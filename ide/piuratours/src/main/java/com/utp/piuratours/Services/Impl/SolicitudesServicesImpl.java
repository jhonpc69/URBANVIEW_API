package com.utp.piuratours.Services.Impl;


import com.utp.piuratours.Builder.ImagenesBuilder;
import com.utp.piuratours.Builder.SolicitudesBuilder;
import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudBandejaDto;
import com.utp.piuratours.Dto.Solicitudes.SolicitudRegistroDto;
import com.utp.piuratours.Models.Imagenes;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Models.Solicitudes;
import com.utp.piuratours.Repository.SolicitudesRepository;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Services.*;
import com.utp.piuratours.Util.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SolicitudesServicesImpl implements SolicitudesService {


    private final LocalesService localesService;
    private final CategoriaService categoriaService;
    private final UsuariosRepository usuariosRepository;
    private final SolicitudesRepository solicitudesRepository;
    private final UsuariosService usuariosService;
    private final ImagenesService imagenesService;
    @Lazy
    private final PublicacionesService publicacionesService;

    @Override
    public SolicitudRegistroDto grabarSolicitud(Integer idUsuario, String ruc, String nombreLocal, String telefono, String diasSemana, String horaApertura, String horaCierre, Integer idCategoria, List<MultipartFile> imagenes, MultipartFile fichaRuc, String descripcion, String ubicacion, String linkFacebook, String linkInstagram, String linkMaps,MultipartFile imgPortada,MultipartFile imgDestacado) throws IOException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String formattedDate = currentDate.format(formatter);

        //CREAR lOCAL
        Locales localBuild = Locales.builder()
                .nombreLocal(nombreLocal)
                .telefono(telefono)
                .ruc(ruc)
                .descripcion(descripcion)
                .ubicacion(ubicacion)
                .horaApertura(horaApertura)
                .horaCierre(horaCierre)
                .estado("0")
                .Link_Facebook(linkFacebook.isEmpty() ? null : linkFacebook)
                .Link_Instagram(linkInstagram.isEmpty() ? null : linkInstagram)
                .Link_Maps(linkMaps.isEmpty() ? null : linkMaps)
                .imgPortada(imgPortada.getBytes())
                .imgDestacado(imgDestacado.getBytes())
                .idCategoria(categoriaService.obtenerLocalPorId(idCategoria)).build();

        Locales local = localesService.grabarLocal(localBuild);
        //

        local.setRutaPdf(saveFileToDesktop(fichaRuc));
        localesService.grabarLocal(localBuild);
        //CREAMOS LA SOLICITUD

        Solicitudes solicitud = Solicitudes.builder()
                .idLocal(local)
                .idUsuario(usuariosService.obtenerUsuarioPorId(idUsuario))
                .Estado("EN PROCESO")
                .fechaRegistro(formattedDate)
                .build();
        solicitudesRepository.save(solicitud);

        // Guardar imágenes

        List<Imagenes> imagenesList = imagenes.stream().map(file -> {
            Imagenes imagen;
            try {
                imagen = Imagenes.builder()
                        .idSolicitud(solicitud)
                        .imagen(file.getBytes())
                        .descripcion(file.getOriginalFilename())
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return imagenesService.guardaImagen(imagen);
        }).toList();

        return SolicitudRegistroDto.builder()
                .idSolicitud(solicitud.getIdSolicitud())
                .idCategoria(local.getIdCategoria().getIdCategoria())
                .idLocal(local.getIdLocal())
                .idUsuario(idUsuario)
                .nombreLocal(local.getNombreLocal())
                .telefono(local.getTelefono())
                .descripcion(local.getDescripcion())
                .ubicacion(local.getUbicacion())
                .horaApertura(local.getHoraApertura())
                .horaCierre(local.getHoraCierre())
                .Estado(solicitud.getEstado())
                .fechaRegistro(solicitud.getFechaRegistro())
                .linkFacebook(local.getLink_Facebook())
                .linkInstagram(local.getLink_Instagram())
                .linkMaps(local.getLink_Maps())
                .imagenes(ImagenesBuilder.BuildearImagenes(imagenesList)).build();
    }

    @Override
    public PublicacionesDto decisionSolicitud(Integer idSolicitud, String decision, String comentario) {
        Solicitudes solicitud = solicitudesRepository.findById(idSolicitud).get();
        Locales local = localesService.obtenerLocalPorId(solicitud.getIdLocal().getIdLocal());
        log.info("paslo por aca ");
        Publicaciones publicacion = new Publicaciones();
        if (Util.convertirStringBoolean(decision)) {
            solicitud.setEstado("ACEPTADA");
            local.setEstado("1");
            publicacion = publicacionesService.GuardarPublicacion(Publicaciones.builder()
                    .idLocal(local)
                    .Titulo(local.getNombreLocal())
                    .fechaPublicacion(Util.obtenerFechaActualString())
                    .estado("1")
                    .build());
            return PublicacionesDto.builder()
                    .idPublicacion(publicacion.getIdPublicacion())
                    .idLocal(publicacion.getIdLocal().getIdLocal())
                    .estado(publicacion.getEstado())
                    .Titulo(publicacion.getTitulo())
                    .descripcion(publicacion.getIdLocal().getDescripcion())
                    .horaApertura(publicacion.getIdLocal().getHoraApertura())
                    .horaCierre(publicacion.getIdLocal().getHoraCierre())
                    .imagenes(imagenesService.obtenerImagenesPorIdSolicitud(publicacion.getIdLocal().getIdLocal()))
                    .fechaPublicacion(publicacion.getFechaPublicacion())
                    .build();
        } else {
            log.info("paslo por aca rechazo  ");
            solicitud.setEstado("RECHAZADA");
            solicitud.setComentarioAdmin(comentario);
            solicitudesRepository.save(solicitud);
            return null;
        }


    }

    @Override
    public Solicitudes obtenerSolicitudPorLocal(Locales idLocal) {
        return solicitudesRepository.findByIdLocal(idLocal).get(0);
    }

//    @Override
//    public List<SolicitudBandejaDto> listarSolicitudes() {
//        List<Solicitudes> solicitudes = solicitudesRepository.findAll();
//
//        return SolicitudesBuilder.BuildearSolicitudes(solicitudes,localesService,imagenesService);
//    }


    @Override
    public List<SolicitudBandejaDto> listarSolicitudes() {
        List<Solicitudes> solicitudes = solicitudesRepository.findAll();
        List<Solicitudes> solicitudesEnProceso = solicitudes.stream()
                .filter(solicitud -> "EN PROCESO".equals(solicitud.getEstado()))
                .collect(Collectors.toList());

        return SolicitudesBuilder.BuildearSolicitudes(solicitudesEnProceso, localesService, imagenesService);
    }

    @Override
    public List<SolicitudBandejaDto> listarSolicitudesPorIdUsuario(Integer idUsuario) {

        List<Solicitudes> solicitudes = solicitudesRepository.findByIdUsuario(usuariosRepository.findById(idUsuario).get());
        return SolicitudesBuilder.BuildearSolicitudes(solicitudes, localesService, imagenesService);
    }

    @Override
    public byte[] descargarAdjunto(String ruta) {
        try {
            Path filePath = Paths.get(ruta);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.error("Error al descargar el archivo desde la ruta especificada", e);
            return null;
        }
    }

    public String guardarAdjunto(Integer local, MultipartFile archivo, String rutaArchivo) {
//        String sftpDestinationDir = "/home/ChapaTuPointFiles/FichasRuc";
//        String fullPath = sftpDestinationDir.concat("/").concat(String.valueOf(local)).concat("/");
//        String originalFilename = Objects.requireNonNull(archivo.getOriginalFilename()).replace(" ", "_");
//        String filePath = fullPath.concat(originalFilename);
//
//        try (InputStream inputStream = archivo.getInputStream()) {
//            // Crear directorio si no existe
//            crearDirectorioProcesoServerSftp(fullPath);
//
//            // Subir archivo
//            channelSftp.put(inputStream, filePath);
//        } catch (IOException | SftpException e) {
//            log.error("Error al subir el archivo al servidor SFTP", e);
//            return null;
//        }
//        return filePath;
        return "sila";
    }


    private void crearDirectorioProcesoServerSftp(String rutaDirectorio)
   //         throws SftpException
    {
//        String[] folders = rutaDirectorio.split("/");
//        StringBuilder path = new StringBuilder("/");
//        for (String folder : folders) {
//            if (folder.length() > 0) {
//                path.append(folder).append("/");
//                try {
//                    channelSftp.cd(path.toString());
//                } catch (SftpException e) {
//                    channelSftp.mkdir(path.toString());
//                    channelSftp.cd(path.toString());
//                }
//            }
    }




       private String saveFileToDesktop(MultipartFile file) throws IOException {
        // Definir la ruta del directorio en el escritorio
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop" + File.separator + "files";

        // Crear el directorio si no existe
        Path directoryPath = Paths.get(desktopPath);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Obtener el nombre original del archivo y crear la ruta completa
        String originalFilename = file.getOriginalFilename();
        Path filePath = directoryPath.resolve(originalFilename);

        // Guardar el archivo en la ruta especificada
        Files.write(filePath, file.getBytes());

        // Devolver la ruta completa donde se guardó el archivo
        return filePath.toString();
    }


    }


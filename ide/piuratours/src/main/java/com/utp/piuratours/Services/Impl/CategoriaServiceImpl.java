package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Builder.CategoriaBuilder;
import com.utp.piuratours.Dto.Categorias.CategoriasDto;
import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Repository.CategoriaRepository;
import com.utp.piuratours.Services.CategoriaService;
import com.utp.piuratours.Services.ComentariosService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    @Override
    public Categoria obtenerLocalPorId(Integer id) {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public List<CategoriasDto> listarCategorias() {
        List<Categoria> list = categoriaRepository.findAll();
        return CategoriaBuilder.BuildearCategoria(list);
    }

    @Override
    public CategoriasDto agregarCategoria(String descripcion, MultipartFile imagen) throws IOException {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(descripcion);

        // 1. Leer imagen original desde el MultipartFile
        BufferedImage originalImage = ImageIO.read(imagen.getInputStream());

        // 2. Crear imagen redimensionada
        int targetWidth = 1920;
        int targetHeight = 1080;
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        // 3. Convertir a byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos); // o "png" si prefieres
        byte[] imageBytes = baos.toByteArray();

        // 4. Guardar en la entidad
        categoria.setImagen(imageBytes);
        categoriaRepository.save(categoria);

        // 5. Retornar DTO
        return CategoriasDto.builder()
                .idCategoria(categoria.getIdCategoria())
                .NombreCategoria(categoria.getNombreCategoria())
                .build();
    }

    @Override
    public String eliminarCategoria(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        return "Se elimino Correctamente";
    }
}

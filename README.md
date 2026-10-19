# UrbanView API: desarrollo y prueba de login

La API usa Java 17, Spring Boot 3 y PostgreSQL. El cliente está en `jhonpc69/URBANVIEW_CLIENTE`, rama `dev`.

## Probar en tu computadora

Necesitas Git, un **JDK 17** (no solo Java Runtime), Docker con Compose y, para el cliente, Node 18 con npm. Usa las ramas con estos cambios; `main` solo contiene el README inicial.

En una terminal, desde la raíz de este repositorio:

```sh
docker compose up -d --wait
cd ide/piuratours
bash ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

En Windows puedes usar `mvnw.cmd` en lugar de `bash ./mvnw`.

En otra terminal, desde el repositorio del cliente:

```sh
cd ide/ChapaTuPointFrontend
npm ci
npm start
```

Abre `http://localhost:4200` en el navegador de **esa misma computadora**. Si ejecutas estos comandos en un entorno cloud, esos puertos pertenecen a la máquina cloud; tu `localhost` no los muestra automáticamente. No se publica una web al iniciar estos procesos.

## Usuario de prueba

El perfil `dev` crea una cuenta **cliente**, solo si no existe:

- Correo: `demo@urbanview.test`
- Contraseña: `UrbanViewDemo2026!`
- DNI: `00000001`

No es una cuenta administradora. El arranque no cambia las contraseñas de usuarios existentes. El perfil de desarrollo y sus datos demo no son para producción.

1. Pulsa **Login** en la página y escribe el correo y la contraseña anteriores.
2. Pulsa **Enviar código**.
3. Abre el buzón de prueba en `http://localhost:8025` y copia los seis dígitos del correo de UrbanView.
4. Vuelve a la aplicación, introduce el código y pulsa **Verificar e ingresar**.
5. Debe aparecer **Usuario** en la cabecera. Puedes abrir el formulario de registro de locales como usuario autenticado.

Mailpit captura el correo en Docker: no necesitas Gmail ni se envían correos reales. Los códigos duran diez minutos, son de un solo uso y no se guardan como sesión hasta verificarlos. Volver a pulsar Enviar código durante ese plazo reenvía el mismo código sin extender su vencimiento; tras usarlo o expirar se genera uno nuevo.

## Servicios y datos

- Cliente: puerto 4200; `/api` se redirige a la API mediante el proxy de Angular.
- API: puerto 8081. Comprobación: `curl http://localhost:8081/Categorias/listarCategorias`.
- PostgreSQL: puerto 5432, base `chapatupoint`, usuario `postgres` y contraseña local `urbanview-local`.
- Mailpit: SMTP en 1025 y buzón web en 8025.

Los puertos Docker solo se enlazan a loopback. `docker compose stop` detiene los servicios sin borrar los datos; `docker compose up -d --wait` los reanuda. Detén API y cliente con Ctrl+C en sus terminales. Si un puerto está ocupado, identifica el servicio anterior antes de iniciar otro.

La base persiste en el volumen `urbanview-db`. El perfil `dev` actualiza el esquema y siembra tipos de usuario, categorías y la cuenta demo en una base nueva. No ejecutes `ide/init.sql`: corresponde a un esquema anterior. Para configurar una base propia se usan `DB_URL`, `DB_USER` y `DB_PASS`; nunca ejecutes el perfil `dev` sobre producción.

Sin el perfil `dev`, debes proporcionar `JWT_SECRET` (clave Base64 suficientemente larga), una base preparada y la configuración SMTP apropiada (`MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `MAIL_AUTH`, `MAIL_STARTTLS`). No hay credenciales de correo reales incluidas en la configuración.

## Pruebas

Desde `ide/piuratours`:

```sh
bash ./mvnw verify
```

Las pruebas de controlador validan códigos expirados, usados e incorrectos, reutilización de la fila de verificación y emisión de sesión solo después de autenticar. No necesitan Docker. Para la prueba funcional de extremo a extremo, usa el recorrido de login anterior con Docker y ambos servidores activos.

package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    private Properties properties = new Properties();

    public GetProperties() {
        // Usar ClassLoader para cargar el archivo de propiedades desde el directorio de recursos
        try (InputStream config = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (config == null) {
                throw new RuntimeException("El archivo config.properties no se encuentra en el classpath");
            }
            properties.load(config);
        } catch (IOException e) {
            // Manejar errores de IO de manera adecuada
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
    }

    public String getString(String propName) {
        return properties.getProperty(propName);
    }

}


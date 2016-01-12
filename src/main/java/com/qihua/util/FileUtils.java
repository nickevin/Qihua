package com.qihua.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.qihua.common.Constants;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class FileUtils {

    public FileUtils() {
    }

    public static String load(String path) throws IOException {
        return read(new File(path));
    }

    public static String read(File file) throws IOException {
        if (!file.exists()) {
            return null;
        }

        StringBuffer result = new StringBuffer();
        FileReader fr = new FileReader(file);
        for (int ch = 0; (ch = fr.read()) != -1;) {
            result.append((char) ch);
        }

        fr.close();

        return result.toString();
    }

    public static void write(String path, String content) {
        FileWriter write = null;

        try {
            write = new FileWriter(path);
            write.write(content, 0, content.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (write != null) {
                try {
                    write.flush();
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void write(String path, InputStream is) {
        OutputStream os = null;

        try {
            os = new FileOutputStream(new File(path));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    // outputStream.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static String getFileNameWithOutSuffix(String name) {
        if (name == null || name.equals("")) {
            return "";
        } else {
            return name.substring(0, name.lastIndexOf("."));
        }
    }

    public static String readProperties(String key) {
        Properties config = new Properties();

        try {
            config.load(new FileUtils().getClass().getClassLoader()
                    .getResourceAsStream(Constants.APPLICATION_PROPERTIES));

            // Resource resource = new
            // ClassPathResource("/application.properties");
            // Properties props =
            // PropertiesLoaderUtils.loadProperties(resource);
            //
            // return props.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config.getProperty(key);
    }

    public static String randomName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;

        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }

        return fileName;
    }

    public static void download(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        BufferedInputStream is = null;
        BufferedOutputStream os = null;

        try {
            String filename = FileUtils.generateFilename(request, StringUtils.getFilename(url));

            response.addHeader("Content-disposition", "attachment; filename=" + filename);
            response.addHeader("Content-Length", String.valueOf(new File(url).length()));

            is = new BufferedInputStream(new FileInputStream(url));
            os = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
                os.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }

            if (os != null) {
                os.close();
            }
        }
    }

    public static String generateFilename(HttpServletRequest request, String filename)
            throws UnsupportedEncodingException {
        String agent = request.getHeader("User-Agent");
        boolean isMSIE = agent != null && agent.indexOf("MSIE") != -1;
        if (isMSIE) {
            return URLEncoder.encode(filename, "UTF-8");
        } else {
            return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
    }
}
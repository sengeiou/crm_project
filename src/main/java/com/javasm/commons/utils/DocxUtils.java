package com.javasm.commons.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class DocxUtils {
    public static void createDocx(Object data, String templateName, OutputStream out) throws Exception {
        Configuration c = new Configuration(Configuration.VERSION_2_3_31);
        c.setDefaultEncoding("UTF-8");
        c.setClassLoaderForTemplateLoading(DocxUtils.class.getClassLoader(),"/docx");
        Template template = c.getTemplate(templateName);
        Writer w = new OutputStreamWriter(out,"UTF-8");
        template.process(data,w);
    }
}

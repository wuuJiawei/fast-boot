package com.w.generator.target;

import com.w.generator.model.Entity;
import com.w.generator.target.AutoGen;
import com.w.generator.target.BaseTarget;
import com.w.generator.target.MdGen;
import com.w.generator.target.html.EditGen;
import com.w.generator.target.html.IndexGen;
import com.w.generator.target.java.ControllerGen;
import com.w.generator.target.java.DaoGen;
import com.w.generator.target.java.EntityGen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MavenProjectTarget extends BaseTarget {
    Entity entity;
    String basePackage;
    String basePackagePath = null;
    String targetPath = null;

    public MavenProjectTarget(Entity entity, String basePackage) {
        this.entity = entity;
        this.basePackage = basePackage;
        this.basePackagePath = basePackage.replace('.', '/');
    }

    @Override
    public void flush(AutoGen gen, String content) {
        String name = gen.getName();
        String target = null;
        if (gen instanceof IndexGen) {
            target = getResourcePath() + "/templates/" + this.urlBase + "/" + entity.getCode() + "/" + name;
        } else if (gen instanceof EditGen) {
            target = getResourcePath() + "/templates/" + this.urlBase + "/" + entity.getCode() + "/" + name;
        } else if (gen instanceof MdGen) {
            target = getResourcePath() + "/sql/" + this.urlBase + "/" + name;
        } else if (gen instanceof EntityGen) {
            target = getSrcPath() + "/" + basePackagePath + "/model/" + name;
        } else if (gen instanceof DaoGen) {
            target = getSrcPath() + "/" + basePackagePath + "/dao/" + name;
        } else if (gen instanceof ControllerGen) {
            target = getSrcPath() + "/" + basePackagePath + "/controller/" + name;
        }

        if (target == null) {
            return;
        }
        flush(target, content);

    }

    protected void flush(String path, String content) {

        FileWriter fw;
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fw = new FileWriter(new File(path));
            fw.write(content);
            fw.close();
            System.out.println(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    private String getSrcPath() {

        return getRootPath() + File.separator + "src/main/java";
    }

    private String getResourcePath() {

        return getRootPath() + File.separator + "src/main/resources";
    }

    public String getRootPath() {
        if (targetPath != null) {
            return targetPath;
        } else {
            return detectRootPath();
        }

    }

    public static String detectRootPath(String module) {
        return detectRootPath() + "/" + module;
    }

    public static String detectRootPath() {
        String srcPath;
        String userDir = System.getProperty("user.dir");
        if (userDir == null) {
            throw new NullPointerException("用户目录未找到");
        }

        return userDir;
    }

}

package com.mbg;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.mbg.domain.ModulePath;
import com.mbg.utils.GeneratePanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class MbgToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        String path = project.getBasePath();
//        System.out.println(path);
        ModuleManager moduleManager = ModuleManager.getInstance(project);
        Module[] modules = moduleManager.getModules();
//        String moduleInfoStr = "all modules---------------";
        List<ModulePath> modulePathList = new ArrayList<>();
        for (Module module : modules) {
            ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
//            moduleInfoStr = moduleInfoStr + "\n" + module.getName();
            List<VirtualFile> sourceRoots = moduleRootManager.getSourceRoots(JavaSourceRootType.SOURCE);
            List<VirtualFile> resourceRoots = moduleRootManager.getSourceRoots(JavaResourceRootType.RESOURCE);

            if (!sourceRoots.isEmpty() && !resourceRoots.isEmpty()) {
                ModulePath modulePath = new ModulePath();
                modulePath.setName(module.getName());
                modulePath.setSourcePath(sourceRoots.get(0).getPath());
                modulePath.setResourcePath(resourceRoots.get(0).getPath());
                modulePathList.add(modulePath);
            }

        }
//        printInfo(moduleInfoStr);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(new GeneratePanel(modulePathList).generateJpanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

//    public void printInfo(String str) {
//        JFrame jFrame = new JFrame();
//        JPanel jPanel = new JPanel();
//        JTextArea jTextArea = new JTextArea();
//        jTextArea.setText(str);
//        jPanel.add(jTextArea);
//        jFrame.add(jPanel);
//        //使frame大小合理
//        jFrame.pack();
//        jFrame.setVisible(true);
//    }
}

package com.mbg.utils;


import com.google.gson.Gson;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.ComboBox;
import com.mbg.constant.DatabaseTypeEnum;
import com.mbg.domain.DatabaseConfig;
import com.mbg.domain.ModulePath;
import com.mbg.domain.MybatisGeneratorConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GeneratePanel {

    private static Gson gson=new Gson();

    private static final String CONFIG_KEY="wulang.mbg.test1";

    private JTextField hostText=new JTextField(5);
    private JTextField portText=new JTextField(5);
    private JTextField dataBaseText=new JTextField(5);
    private JTextField userNameText=new JTextField(5);
    private JTextField passWordText=new JTextField(5);

    private JTextField mapperText=new JTextField(9);
    private JTextField modelText=new JTextField(9);
    private JTextField xmlText=new JTextField(9);

    private JTextField tableText=new JTextField(9);
    private JTextField schemaText=new JTextField(9);
    private JTextField domainObjectText=new JTextField(9);
    private JTextField mapperNameText=new JTextField(9);

    private JComboBox<String> dababaseSelectComboBox=new ComboBox<>();
    private JComboBox<ModulePath> modelSelectComboBox=new ComboBox<>();

    private JPanel jPanelRoot=new JPanel();
    private List<ModulePath> modulePathList;

    public GeneratePanel(List<ModulePath> modulePathList){
        this.modulePathList=modulePathList;
        setDefault();
    }

    private void setDefault(){
        PropertiesComponent propertiesComponent=PropertiesComponent.getInstance();
        String value=propertiesComponent.getValue(CONFIG_KEY);
        if(value!=null){
            MybatisGeneratorConfig mybatisGeneratorConfig=gson.fromJson(value,MybatisGeneratorConfig.class);
            hostText.setText(mybatisGeneratorConfig.getHost());
            portText.setText(mybatisGeneratorConfig.getPort());
            dataBaseText.setText(mybatisGeneratorConfig.getDatabaseName());
            userNameText.setText(mybatisGeneratorConfig.getUserName());
            passWordText.setText(mybatisGeneratorConfig.getPassword());

            mapperText.setText(mybatisGeneratorConfig.getMapperPackage());
            modelText.setText(mybatisGeneratorConfig.getModelPackage());
            xmlText.setText(mybatisGeneratorConfig.getXmlPackage());

            tableText.setText(mybatisGeneratorConfig.getTable());
            schemaText.setText(mybatisGeneratorConfig.getSchema());
            domainObjectText.setText(mybatisGeneratorConfig.getDomainObjectName());
            mapperNameText.setText(mybatisGeneratorConfig.getMapperName());
        }
    }

    public JPanel generateJpanel(){
        jPanelRoot.setLayout(new GridLayout(1,4));
        jPanelRoot.add(generateDatabaseConfigJpanel());
        jPanelRoot.add(generatePackageConfigPanel());
        jPanelRoot.add(generateTableConfigPanel());
        jPanelRoot.add(generateOperatePanel());
        return jPanelRoot;
    }

    private JPanel generateDatabaseConfigJpanel(){
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(5,1));

        JPanel hostPanel=new JPanel();
        JLabel hostLabel=new JLabel("host");
        hostPanel.add(hostLabel);
        hostPanel.add(hostText);
        jPanel.add(hostPanel);

        JPanel portPanel=new JPanel();
        JLabel portLabel=new JLabel("port");

        portPanel.add(portLabel);
        portPanel.add(portText);
        jPanel.add(portPanel);

        JPanel dataBasePanel=new JPanel();
        JLabel dataBaseLabel=new JLabel("数据库名称");

        dataBasePanel.add(dataBaseLabel);
        dataBasePanel.add(dataBaseText);
        jPanel.add(dataBasePanel);

        JPanel userNamePanel=new JPanel();
        JLabel userNameLabel=new JLabel("用户名");

        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameText);
        jPanel.add(userNamePanel);

        JPanel passWordPanel=new JPanel();
        JLabel passWordLabel=new JLabel("密码");

        passWordPanel.add(passWordLabel);
        passWordPanel.add(passWordText);
        jPanel.add(passWordPanel);

        return jPanel;
    }

    private JPanel generatePackageConfigPanel(){
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(3,1));

        JPanel mapperPanel=new JPanel();
        JLabel mapperLabel=new JLabel("mapper接口包名");

        mapperPanel.add(mapperLabel);
        mapperPanel.add(mapperText);
        jPanel.add(mapperPanel);

        JPanel modelPanel=new JPanel();
        JLabel modelLabel=new JLabel("model类包名");

        modelPanel.add(modelLabel);
        modelPanel.add(modelText);
        jPanel.add(modelPanel);

        JPanel xmlPanel=new JPanel();
        JLabel xmlLabel=new JLabel("xml文件包名");

        xmlPanel.add(xmlLabel);
        xmlPanel.add(xmlText);
        jPanel.add(xmlPanel);

        return jPanel;
    }

    private JPanel generateTableConfigPanel(){
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(4,1));

        JPanel tablePanel=new JPanel();
        JLabel tableLabel=new JLabel("表名");

        tablePanel.add(tableLabel);
        tablePanel.add(tableText);
        jPanel.add(tablePanel);

        JPanel schemaPanel=new JPanel();
        JLabel schemaLabel=new JLabel("schema名");

        schemaPanel.add(schemaLabel);
        schemaPanel.add(schemaText);
        jPanel.add(schemaPanel);

        JPanel domainObjectPanel=new JPanel();
        JLabel domainObjectLabel=new JLabel("domain对象名");

        domainObjectPanel.add(domainObjectLabel);
        domainObjectPanel.add(domainObjectText);
        jPanel.add(domainObjectPanel);

        JPanel mapperNamePanel=new JPanel();
        JLabel mapperNameLabel=new JLabel("mapper接口名");

        mapperNamePanel.add(mapperNameLabel);
        mapperNamePanel.add(mapperNameText);
        jPanel.add(mapperNamePanel);

        return jPanel;
    }

    private JPanel generateOperatePanel(){
        JPanel jPanel=new JPanel(new GridLayout(3,1));

        JPanel dababaseSelectPanel=new JPanel();
        JLabel dababaseSelectLabel=new JLabel("选择数据库");

        dababaseSelectComboBox.addItem(DatabaseTypeEnum.POSTGRESQL);
        dababaseSelectComboBox.addItem(DatabaseTypeEnum.MYSQL);
        dababaseSelectComboBox.setEditable(false);

        dababaseSelectPanel.add(dababaseSelectLabel);
        dababaseSelectPanel.add(dababaseSelectComboBox);
        jPanel.add(dababaseSelectPanel);

        JPanel modelSelectPanel=new JPanel();
        JLabel modelSelectLabel=new JLabel("选择模块");

        for(ModulePath modulePath:modulePathList){
            modelSelectComboBox.addItem(modulePath);
        }
        modelSelectComboBox.setEditable(false);

        modelSelectPanel.add(modelSelectLabel);
        modelSelectPanel.add(modelSelectComboBox);
        jPanel.add(modelSelectPanel);

        JPanel generateButtonPanel=new JPanel();
        JLabel generateButtonLabel=new JLabel("点击按钮生成");
        JButton generateButton=new JButton("生成");

        generateButtonPanel.add(generateButtonLabel);
        generateButtonPanel.add(generateButton);
        jPanel.add(generateButtonPanel);


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MybatisGeneratorConfig mybatisGeneratorConfig=new MybatisGeneratorConfig();
                mybatisGeneratorConfig.setMapperPackage(mapperText.getText());
                mybatisGeneratorConfig.setModelPackage(modelText.getText());
                mybatisGeneratorConfig.setXmlPackage(xmlText.getText());

                mybatisGeneratorConfig.setTable(tableText.getText());
                mybatisGeneratorConfig.setSchema(schemaText.getText());
                mybatisGeneratorConfig.setMapperName(mapperNameText.getText());
                mybatisGeneratorConfig.setDomainObjectName(domainObjectText.getText());

                mybatisGeneratorConfig.setHost(hostText.getText());
                mybatisGeneratorConfig.setPort(portText.getText());
                mybatisGeneratorConfig.setDatabaseName(dataBaseText.getText());
                mybatisGeneratorConfig.setUserName(userNameText.getText());
                mybatisGeneratorConfig.setPassword(passWordText.getText());

                mybatisGeneratorConfig.setDatabaseType(dababaseSelectComboBox.getSelectedItem().toString());
                ModulePath modulePath=(ModulePath)modelSelectComboBox.getSelectedItem();
                mybatisGeneratorConfig.setSourcesRootPath(modulePath.getSourcePath());
                mybatisGeneratorConfig.setResourcesRootPath(modulePath.getResourcePath());

                System.out.println(mybatisGeneratorConfig);
                String resultStr=MybatisGeneratorUtil.generate(mybatisGeneratorConfig);
                if("成功\n".equals(resultStr)){
                    PropertiesComponent propertiesComponent=PropertiesComponent.getInstance();
                    String value=gson.toJson(mybatisGeneratorConfig);
                    propertiesComponent.setValue(CONFIG_KEY,value);
                }
                JOptionPane.showMessageDialog(jPanelRoot,resultStr,"执行结果",JOptionPane.PLAIN_MESSAGE);
            }
        });
        return jPanel;
    }

    private JPanel generateDatabaseConfigListPanel(int rows){
        final JPanel jPanel=new JPanel(new GridLayout(rows,5));
        DatabaseConfig databaseConfig=new DatabaseConfig();
        databaseConfig.setHost("host");
        databaseConfig.setPort("port");
        databaseConfig.setDatabaseName("数据库名");
        databaseConfig.setUserName("用户名");
        databaseConfig.setPassword("密码");
        addLabelToPanel(jPanel,databaseConfig);
        return jPanel;
    }

    private JPanel generateLeftPanel(String text){

        JPanel jPanel=new JPanel();
        JLabel jLabel=new JLabel(text);
        jPanel.add(jLabel);
        return jPanel;
    }

    private void addLabelToPanel(JPanel jPanel,DatabaseConfig databaseConfig){
        List<JLabel> jLabelList=new ArrayList<>();
        jLabelList.add(new JLabel(databaseConfig.getHost()));
        jLabelList.add(new JLabel(databaseConfig.getPort()));
        jLabelList.add(new JLabel(databaseConfig.getDatabaseName()));
        jLabelList.add(new JLabel(databaseConfig.getUserName()));
        jLabelList.add(new JLabel(databaseConfig.getPassword()));

        for(JLabel jLabel:jLabelList){
            jLabel.setForeground(Color.red);
            jPanel.add(jLabel);
        }
    }
}

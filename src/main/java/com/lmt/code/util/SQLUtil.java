package com.lmt.code.util;

import com.lmt.code.context.GeneratorContext;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @description 根据数据库类型，sqlId获取SQL语句
 *
 * @author bazhandao
 * @date 2018/11/2 11:23
 * @since JDK1.8
 */
public class SQLUtil {

    /**
     * @description根据数据库类型，sqlId获取SQL语句
     * @authro bazhandao
     * @date 2018-11-02
     * @param dbType
     * @param sqlId
     * @return
     */
    public static String getSql(GeneratorContext generatorContext, String dbType, String sqlId) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(SQLUtil.class.getClassLoader().getResourceAsStream("sqlmapper/" + dbType + ".xml"));
            NodeList nodeList = document.getElementsByTagName("sql");
            for(int i = 0;i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NamedNodeMap attrs = node.getAttributes();
                Node id = attrs.getNamedItem("id");
                if(id.getNodeValue().equals(sqlId)) {
                    String sql = node.getTextContent().trim();
                    if(generatorContext != null) {
                        String schema = generatorContext.getSchema() == null ? "" : generatorContext.getSchema();
                        sql = sql.replace("${schema}", schema);
                    }
                    return sql;
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("sql不存在,请检查sqlmapper是否存在！,dbType=" + dbType + ",sqlId=" + sqlId + ",path=sqlmapper/" + dbType + ".xml");
    }

}

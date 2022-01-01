package pers.YuanchenBei.dbwork.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportExport {

    public static void export() throws JRException {

        Db_utils dbUtils=new Db_utils();
        Connection con=null;
        try {
            con=dbUtils.getCon();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> rpt = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport("E:/report/report1.jasper", rpt,con);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "E:/report/report1.pdf");
    }
}

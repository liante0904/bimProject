package com.bridgeimpact.renewal.common;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class FileDownloadView extends AbstractView {


    public FileDownloadView() {

        //content type을 지정.

        setContentType("apllication/download; charset=utf-8");


    }


    @Override

    protected void renderMergedOutputModel(Map<String, Object> model,

                                           HttpServletRequest req, HttpServletResponse res) throws Exception {

        // TODO Auto-generated method stub


        File file = (File) model.get("downloadFile");
        String OriginalFileName = (String) model.get("OriginalFileName");
        logger.info("OriginalFileName : " + OriginalFileName );
        res.setContentType(getContentType());
        res.setContentLength((int) file.length());
        res.setHeader("Content-Disposition", "attachment; filename=\"" +
                java.net.URLEncoder.encode(OriginalFileName, "utf-8") + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");


        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


        out.flush();

    }
}

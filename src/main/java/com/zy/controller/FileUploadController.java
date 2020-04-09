package com.zy.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.zy.service.ActivateempService;
import com.zy.service.Impl.FileUploadServiceImpl;
import com.zy.vo.base.RespBean;
import com.zy.vo.response.FileUploadResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class FileUploadController
 * @create 2020-03-19 21:36
 */
@Api("管理员端登录以及员工信息")
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    FileUploadServiceImpl fileUploadServiceImpl;
    @Autowired
    ActivateempService activateempService;

    /**
     * @return FileUploadResult
     * @author 管理员端
     * @desc 文件上传到oss, 并插入数据库
     * @Param uploadFile
     */
    @PostMapping("/upload/{empId}/{name}")
    @ResponseBody
    public RespBean upload(@PathVariable long empId, @PathVariable String name, @RequestParam("file") MultipartFile uploadFile)
            throws Exception {
        FileUploadResponse fileUploadResponse = this.fileUploadServiceImpl.upload(uploadFile);
        int result = activateempService.updatePhoto(empId, name, fileUploadResponse.getName());
        if (result > 0) {
            return RespBean.ok("上传成功！", fileUploadResponse);
        } else {
            return RespBean.error("上传失败！");
        }
    }

    /**
     * @return FileUploadResult
     * @author lastwhisper
     * @desc 文件上传到oss
     * @Param uploadFile
     */
    @PostMapping("/upload")
    @ResponseBody
    public RespBean upload(@RequestParam("file") MultipartFile uploadFile)
            throws Exception {
        FileUploadResponse fileUploadResponse = this.fileUploadServiceImpl.upload(uploadFile);
        if (fileUploadResponse.getResponse() == "success") {
            return RespBean.ok("上传成功！", fileUploadResponse);
        } else {
            return RespBean.error("上传失败！");
        }

    }


    /**
     * @return FileUploadResult
     * @desc 根据文件名删除oss上的文件
     * http://localhost:8080/file/delete?fileName=images/2019/04/28/1556429167175766.jpg
     * @author lastwhisper
     * @Param objectName
     */
    @RequestMapping("/delete")
    @ResponseBody
    public FileUploadResponse delete(@RequestParam("fileName") String objectName)
            throws Exception {
        return this.fileUploadServiceImpl.delete(objectName);
    }

    /**
     * @return List<OSSObjectSummary>
     * @author lastwhisper
     * @desc 查询oss上的所有文件
     * http://localhost:8080/file/list
     * @Param
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<OSSObjectSummary> list()
            throws Exception {
        return this.fileUploadServiceImpl.list();
    }

    /**
     * @return
     * @author lastwhisper
     * @desc 根据文件名下载oss上的文件
     * @Param objectName
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) throws IOException {
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
        this.fileUploadServiceImpl.exportOssFile(response.getOutputStream(), objectName);
    }
}


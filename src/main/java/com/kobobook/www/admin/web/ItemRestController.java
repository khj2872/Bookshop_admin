package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.repository.ItemRepository;
import com.kobobook.www.admin.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/admin/item")
public class ItemRestController {

    private final S3Uploader s3Uploader;

    private ItemRepository itemRepository;

    public ItemRestController(S3Uploader s3Uploader, ItemRepository itemRepository) {
        this.s3Uploader = s3Uploader;
        this.itemRepository = itemRepository;
    }

    @PostMapping(value="/upload")
    public ResponseEntity<String> uploadItem(MultipartFile file) {

        System.out.println("/upload 확인");
        try {
            String path = s3Uploader.upload(file, "image");

            System.out.println("path : " + path);
            return new ResponseEntity<>(path, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> list(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        Page<Item> itemList = itemRepository.findAll(pageable);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("totalElement", itemList.getTotalElements());
        returnMap.put("totalPage", itemList.getTotalPages());
        returnMap.put("currPage", itemList.getNumber());
        returnMap.put("itemList", itemList.getContent());

        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

//    private static final String uploadPath = "C:\\Users\\KANG\\Desktop\\upload";

//    @PostMapping(value="/upload")
//    public ResponseEntity<Map<String, Object>> uploadItem(MultipartFile file) throws Exception {
//
//        System.out.println("/upload 확인");
//
//        Map<String, Object> map = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
//        String storedFileName = (String)map.get("storedFileName");
//        String path = (String) map.get("path");
//        UploadFile fileInfo = uploadFileService.create(file.getOriginalFilename(), storedFileName, path);
//        map.put("fileInfo", fileInfo);
//        return new ResponseEntity<>(map, HttpStatus.CREATED);
//    }



//    //화면에 저장된 파일을 보여주는 컨트롤러 /년/월/일/파일명 형태로 입력 받는다.
//    // displayFile?fileName=/년/월/일/파일명
//    @GetMapping("/upload/displayFile")
//    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
//        System.out.println("/upload/display fileName : " + fileName);
//        InputStream in = null;
//        ResponseEntity<byte[]> entity = null;
//
//        try {
//            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
//
//            MediaType mType = MediaUtils.getMediaType(formatName);
//
//            HttpHeaders headers = new HttpHeaders();
//
//            in = new FileInputStream(uploadPath + fileName);
//
//
//            if(mType != null) {
//                headers.setContentType(mType);
//            }else {
//                fileName = fileName.substring(fileName.indexOf("_")+1);
//                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
//            }// else
//
//            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
//
//        } catch(Exception e) {
//            e.printStackTrace();
//            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
//        } finally {
//            in.close();
//        }
//
//        return entity;
//    }// displayFile


//    //업로드된 파일 삭제 처리
//    @PostMapping("/upload/deleteFile")
//    public ResponseEntity<String> deleteFile(String fileName) throws Exception {
//
//        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
//
//        MediaType mType = MediaUtils.getMediaType(formatName);
//
//        if(mType != null) {
//            String front = fileName.substring(0, 12);
//            String end = fileName.substring(14);
//            new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
//        }//if
//
//        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
//
//        return new ResponseEntity<String>("deleted", HttpStatus.OK);
//
//    }

}

package jsoup;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jsoup.entity.Company;
import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by player on 2017/8/30.
 */
public class JobsPlay {

    private static Map<String, String> companyMap = new HashMap<>();

    private static List<Company> companyList = new ArrayList<>();

    public static void main(String args[]) throws UnirestException, IOException {
        allPages();
        listCompanyInfo();
        saveFile();
//        jobInfo("http://jobs.51job.com/all/co4118449.html");
    }

    public static void allPages() throws IOException, UnirestException {
        for(int i=1; i<36; i++){
            onePage(i);
        }
    }

    public static void onePage(int pageNumber) throws IOException, UnirestException {
        String url = "http://search.51job.com/list/070300,000000,0000,00,9,99,java,2,"+pageNumber+".html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        search(url);
    }

    public static void search(String url) throws UnirestException, IOException {
        Document doc = queryUrl(url);
        if(doc == null){
            return;
        }
        Elements select = doc.select("div[class=el]");
        select.forEach(element -> {
            element.children().forEach(e -> {
                String company = element.select("span[class=t2]").text();
                String detailUrl = element.select("span[class=t2]").select("a").attr("href");
                String city = element.select("span[class=t3]").text();
//                String salary = element.select("span[class=t4]").text();
                if(company == null || company.trim().length() == 0){
                    return;
                }
                if("异地招聘".equals(city)){
                    return;
                }
                companyMap.put(company, detailUrl);
            });
        });
    }

//    private static void addJob(String companyName, String cidy, String companyDetailUrl, String jobDetailUrl){
//        if(!companyMap.containsKey(companyName)){
//            //获取公司详细信息
//            //获取职位详细信息
//        } else {
//            //获取职位详细信息
//
//        }
//    }

    private static void  listCompanyInfo(){
        companyMap.forEach((k,v) -> {
            try {
                Company company = comInfo(v, k, null);
                if(company != null){
                    companyList.add(company);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });
    }

    private static Company comInfo(String url, String name, String city) throws IOException, UnirestException {
        Document document = queryUrl(url);
        if(document == null){
            return null;
        }
        Elements select = document.select("div[class=con_msg]").select("div[class=in]").select("p");

        String companyInfo = select.text().replaceAll("&nbsp", "").trim();

        String address = document.select("div[class=tCompany_full]").select("div[class=tBorderTop_box]").select("div[class=bmsg inbox]").select("div[class=bmsg inbox]")
                .select("p[class=fp]").text().replaceAll("\"", "").replaceAll("公司地址：", "").trim();

        String xingzhi = document.select("div[class=tCompany_center clearfix]").select("div[class=tHeader tHCop]").select("div[class=in img_on]").select("p[class=ltype]").text().replaceAll("&nbsp", "").trim();
        String[] split = xingzhi.split("\\|");
        String guimo = null;
        if(split.length >= 2){
            xingzhi = split[0].trim();
            guimo = split[1].trim();
        }
        return new Company(name, city, address, companyInfo, xingzhi, guimo);
    }

    private static Document queryUrl(String url) {
        try{
            HttpResponse<InputStream> response = Unirest.get(url)
                    .header("cache-control", "no-cache")
                    .header("Accept-Language", "zh-CN,zh;q=0.8")
                    .header("contentType", "text/html; charset=utf-8")
                    .header("postman-token", "6bbb8884-12e0-1613-61c1-61d538ce89b4")
                    .asBinary();

            InputStream body = response.getBody();
            byte[] data = new byte[body.available()];
            body.read(data);
            String bodyText = new String(data, "gbk");

            return Jsoup.parse(bodyText);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static void saveFile(){
// 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("java-苏州-公司表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("公司");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("描述");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("性质");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("规模");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("地址");
        cell.setCellStyle(style);



        for(int i=0; i<companyList.size(); i++){
            Company company = companyList.get(i);
            row = sheet.createRow( i + 1);
            row.createCell((short) 0).setCellValue(i);
            row.createCell((short) 1).setCellValue(company.getName());
            row.createCell((short) 2).setCellValue(company.getDesc());
            row.createCell((short) 3).setCellValue(company.getXingzhi());
            row.createCell((short) 4).setCellValue(company.getGuimo());
            row.createCell((short) 5).setCellValue(company.getAddress());
        }

        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("E:/company-suzhou-2017.xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

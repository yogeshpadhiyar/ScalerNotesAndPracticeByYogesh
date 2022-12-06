package com.other;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class MkcInterview {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://api.quotable.io/random");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        List<AuthorDetail> authorDetailList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
//            OutputStream ot = httpURLConnection.getOutputStream();
            InputStream in = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line ="";
            while ((line =br.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb);
        }
        String authorName = "";
        Date startDate = new Date();
        HashMap<String,ArrayList<AuthorDetail>> filterByAuthor = filterByDateAndGroupByAuthor(authorDetailList,authorName, startDate );
    }

    private static HashMap<String,ArrayList<AuthorDetail>> filterByDateAndGroupByAuthor(List<AuthorDetail> authorDetailList, String authorName, Date startDate) {
        authorDetailList.stream().filter((a)-> startDate.after(a.dateAdded));
        HashMap<String,ArrayList<AuthorDetail>> hm = new HashMap<>();
        for (AuthorDetail detail : authorDetailList){
            ArrayList<AuthorDetail> authorContentList;
            if(!hm.containsKey(detail.author)){
                authorContentList = new ArrayList<>();
            }else{
                authorContentList = hm.get(detail.author);
            }
            authorContentList.add(detail);
            hm.put(detail.author, authorContentList);
        }
        return hm;
    }
}

class AuthorDetail{
    int _id;
    String content;
    String author;
    List<String> tags;
    String authorSlug;
    String length;
    Date dateAdded;
    Date dateModified;
}
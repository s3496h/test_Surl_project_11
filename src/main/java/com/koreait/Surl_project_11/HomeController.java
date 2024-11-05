package com.koreait.Surl_project_11;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Controller
public class HomeController {
    @GetMapping("a")
    @ResponseBody
    public String hi(
            String age,
            String id
    ) {
        return "안녕, %s번, %s살이야.".formatted(id, age);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            //http://localhost:8080/b?a=20&b=40
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name = "c", defaultValue = "10") int num3
    ) {

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);

        return "a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ) {
        return married ? "기혼" : "미혼";
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married
    ) {
        if (married == null) return "정보 입력해";

        return married ? "기혼" : "미혼";
    }

    @Data
    @ToString
    @AllArgsConstructor
    public static class Person {
        private String name;
        private int age;
    }

    @GetMapping("person1")
    @ResponseBody
    public String person1(
            String name,
            int age
    ) {
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
            Person person
    ) {
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e() {
        int age = 10;
        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public ArrayList f() {
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{1, 2, 3});
        arr.add(new int[]{2});
        arr.add(new int[]{3});
        return arr;
    }
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Post {
        @ToString.Exclude
        @JsonIgnore
        @EqualsAndHashCode.Include
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        @Builder.Default
        private String subject = "제목이야.";
        private String body;
    }
    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>() {{
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목2", "내용2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목3", "내용3"));
            add(new Post(4L, LocalDateTime.now(), LocalDateTime.now(), "제목4", "내용4"));
        }};
        return posts;
    }
    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{
//            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(Post
                    .builder()
                    .id(1L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .body("내용1")
                    .build());
            add(Post
                    .builder()
                    .id(2L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목2")
                    .body("내용2")
                    .build());
            add(Post
                    .builder()
                    .id(3L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목3")
                    .body("내용3")
                    .build());
            add(Post
                    .builder()
                    .id(4L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목4")
                    .body("내용4")
                    .build());
        }};
        return posts;
    }
    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost() {
        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .body("내용1")
                .build();
        System.out.println(post);
        return post;
    }
    @SneakyThrows
    @GetMapping("/posts/2")
    @ResponseBody
    public Post getPost2() {
        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .body("내용1")
                .build();
        Thread.sleep(2000);
        System.out.println(post);
        return post;
    }
}
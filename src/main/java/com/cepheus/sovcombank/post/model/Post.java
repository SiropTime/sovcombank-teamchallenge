package com.cepheus.sovcombank.post.model;

import com.cepheus.sovcombank.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@Table(name = "posts", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "image")
    private String image;
    @ManyToOne
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(description, post.description) && Objects.equals(timeStamp, post.timeStamp)
                && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, timeStamp, author);
    }
}

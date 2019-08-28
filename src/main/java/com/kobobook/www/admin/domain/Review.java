package com.kobobook.www.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private double rating;

    private LocalDateTime regDate;

    private String content;

    public static Review createReview(Member member, Item item, double rating, String content) {
        Review review = new Review();
        review.setMember(member);

        review.setItem(item);
        item.getReviews().add(review);

        review.setRating(rating);
        review.setContent(content);
        review.setRegDate(LocalDateTime.now());

        return review;
    }

}

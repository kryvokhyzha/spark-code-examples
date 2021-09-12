package com.dimajix.training.hashtag2mysql.model.repo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashtagId implements Serializable {
  private ZonedDateTime ts;

  private String topic;
}

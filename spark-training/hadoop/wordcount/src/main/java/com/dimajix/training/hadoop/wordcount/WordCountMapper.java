package com.dimajix.training.hadoop.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  private static final IntWritable one = new IntWritable(1);
  private Text word = new Text();

  @Override
  public void setup(Context context) {}

  @Override
  public void cleanup(Context context) {}

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    StringTokenizer itr = new StringTokenizer(value.toString());
    while (itr.hasMoreTokens()) {
      String token = itr.nextToken().trim();
      word.set(token);
      context.write(word, one);
    }
  }
}

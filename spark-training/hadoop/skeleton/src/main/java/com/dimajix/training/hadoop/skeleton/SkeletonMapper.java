package com.dimajix.training.hadoop.skeleton;

import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class SkeletonMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
  private Text country = new Text();
  private FloatWritable temperature = new FloatWritable();
  private final HashMap<String, String> countries = new HashMap<String, String>();

  @Override
  public void setup(Context context) {}

  @Override
  public void cleanup(Context context) {}

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {}
}

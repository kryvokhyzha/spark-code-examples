package com.dimajix.training.hadoop.skeleton;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

class SkeletonReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
  @Override
  public void setup(Context context) {}

  @Override
  public void cleanup(Context context) {}

  @Override
  public void reduce(Text key, Iterable<FloatWritable> values, Context context)
      throws IOException, InterruptedException {}
}

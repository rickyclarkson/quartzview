package quartzview;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class HelloJob implements Job {
  public HelloJob() {
    System.out.println("here");
  }

  public void execute(JobExecutionContext context) {
    System.out.println("hello");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

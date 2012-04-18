package quartzview;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

class QuartzView {
  private static final Scheduler scheduler;
  static {
    try {
      scheduler = StdSchedulerFactory.getDefaultScheduler();
    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws InterruptedException, SchedulerException {
    scheduler.start();
    scheduleStuff();
    for (;;) {
      printRunningJobs();
      Thread.sleep(1000);
    }
    //scheduler.shutdown();
  }

  private static void scheduleStuff() throws SchedulerException {
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("group1", "job1").build();
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "often").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
    scheduler.scheduleJob(jobDetail, trigger);
  }

  private static void printRunningJobs() throws SchedulerException {
    System.out.println(scheduler.getCurrentlyExecutingJobs());
  }
}

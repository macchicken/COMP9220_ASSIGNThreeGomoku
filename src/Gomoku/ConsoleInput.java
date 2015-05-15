package Gomoku;

import java.util.concurrent.*;

public class ConsoleInput {
  private final TimeUnit unit;

  public ConsoleInput(TimeUnit unit) {
    this.unit = unit;
  }

  public String readLine(long timeout) throws InterruptedException {
    ExecutorService ex = Executors.newSingleThreadExecutor();
    String input = null;
    try {
      // start working
        Future<String> result = ex.submit(new ConsoleInputReadTask());
        try {
          input = result.get(timeout, unit);
        } catch (ExecutionException e) {
          e.getCause().printStackTrace();
        } catch (TimeoutException e) {
          result.cancel(true);
        }
    } finally {
      ex.shutdownNow();
    }
    return input;
  }
}

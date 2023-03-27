public class Main {
  public boolean matches(String text, String regex) {
    Pattern pattern = Pattern.compile(regex);
    MatcherThread matcherThread = new MatcherThread(pattern, text);
    matcherThread.start();
    try {
      matcherThread.join(5000); // wait up to 5 seconds for the thread to finish
      return matcherThread.getResult();
    } catch (InterruptedException e) {
      return false; // thread was interrupted, so return failure value
    }
  }

  private static class MatcherThread extends Thread {
    private final Pattern pattern;
    private final String text;
    private boolean result;

    public MatcherThread(Pattern pattern, String text) {
      this.pattern = pattern;
      this.text = text;
    }

    public boolean getResult() {
      return result;
    }

    public void run() {
      result = pattern.matcher(text).matches();
    }
  }
}

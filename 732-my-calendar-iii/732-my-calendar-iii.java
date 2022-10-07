class MyCalendarThree {
  public int book(int s, int e) {
    timeline.merge(s, 1, Integer::sum);
    timeline.merge(e, -1, Integer::sum);

    int ans = 0;
    int activeEvents = 0;

    for (final int count : timeline.values()) {
      activeEvents += count;
      ans = Math.max(ans, activeEvents);
    }

    return ans;
  }

  private Map<Integer, Integer> timeline = new TreeMap<>();
}
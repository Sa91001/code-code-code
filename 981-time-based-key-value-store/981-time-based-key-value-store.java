class T {
  public String v;
  public int timestamp;
  public T(String v, int timestamp) {
    this.v = v;
    this.timestamp = timestamp;
  }
}

class TimeMap {
  public void set(String k, String v, int timestamp) {
    map.putIfAbsent(k, new ArrayList<>());
    map.get(k).add(new T(v, timestamp));
  }

  public String get(String k, int timestamp) {
    List<T> A = map.get(k);
    if (A == null)
      return "";

    int l = 0;
    int r = A.size();

    while (l < r) {
      final int m = (l + r) / 2;
      if (A.get(m).timestamp > timestamp)
        r = m;
      else
        l = m + 1;
    }

    return l == 0 ? "" : A.get(l - 1).v;
  }

  private Map<String, List<T>> map = new HashMap<>();
}
class Solution {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    final int n = buildings.length;
    if (n == 0)
      return new ArrayList<>();
    if (n == 1) {
      final int lt = buildings[0][0];
      final int rt = buildings[0][1];
      final int ht = buildings[0][2];
      List<List<Integer>> ans = new ArrayList<>();
      ans.add(new ArrayList<>(Arrays.asList(lt, ht)));
      ans.add(new ArrayList<>(Arrays.asList(rt, 0)));
      return ans;
    }

    var ltSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
    var rtSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));
    return merge(ltSkyline, rtSkyline);
  }

  private List<List<Integer>> merge(List<List<Integer>> lt, List<List<Integer>> rt) {
    List<List<Integer>> ans = new ArrayList<>();
    int i = 0; 
    int j = 0; 
    int ltY = 0;
    int rtY = 0;

    while (i < lt.size() && j < rt.size())
      
      if (lt.get(i).get(0) < rt.get(j).get(0)) {
        ltY = lt.get(i).get(1); 
        addPoint(ans, lt.get(i).get(0), Math.max(lt.get(i++).get(1), rtY));
      } else {
        rtY = rt.get(j).get(1);
        addPoint(ans, rt.get(j).get(0), Math.max(rt.get(j++).get(1), ltY));
      }

    while (i < lt.size())
      addPoint(ans, lt.get(i).get(0), lt.get(i++).get(1));

    while (j < rt.size())
      addPoint(ans, rt.get(j).get(0), rt.get(j++).get(1));

    return ans;
  }

  private void addPoint(List<List<Integer>> ans, int x, int y) {
    if (!ans.isEmpty() && ans.get(ans.size() - 1).get(0) == x) {
      ans.get(ans.size() - 1).set(1, y);
      return;
    }
    if (!ans.isEmpty() && ans.get(ans.size() - 1).get(1) == y)
      return;
    ans.add(new ArrayList<>(Arrays.asList(x, y)));
  }
}
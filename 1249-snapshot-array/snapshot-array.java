import java.util.*;

class SnapshotArray {

    List<int[]>[] arr;
    int snapId;

    public SnapshotArray(int length) {
        arr = new ArrayList[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new ArrayList<>();
            arr[i].add(new int[]{0, 0}); // initial value
        }
        snapId = 0;
    }

    public void set(int index, int val) {
        arr[index].add(new int[]{snapId, val});
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = arr[index];

        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid)[0] <= snap_id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return list.get(right)[1];
    }
}
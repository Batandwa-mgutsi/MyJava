
/**
 * vectorObject for drawing lines with gradient 0 < m < 1
 */
public class PtLine extends VectorObject {
  final int x1;
  final int y1;

  public PtLine(int id, int x, int y, int x1, int y1) {
    super(id, x, y);
    this.x1 = x1;
    this.y1 = y1;
  }

  @Override
  public void draw(char[][] matrix) {
    // Using Bresenham's Line Drawing Algorithm

    int x0 = this.x;
    int y0 = this.y;
    int x1 = this.x1;
    int y1 = this.y1;

    boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
    if (steep) {
      int temp = x0;
      x0 = y0;
      y0 = temp;

      temp = x1;
      x1 = y1;
      y1 = temp;
    }

    if (x0 > x1) {
      int temp = x0;
      x0 = x1;
      x1 = temp;

      temp = y0;
      y0 = y1;
      y1 = temp;
    }

    int ys = -1;
    if (y0 < y1)
      ys = 1;

    double m = Math.abs(y1 - y0) / (x1 - x0);
    int y = y0;
    double error = 0;

    for (int x = x0; x <= x1; x++) {
      if (steep)
        matrix[x][y] = '*';
      else
        matrix[y][x] = '*';

      error = error + m;
      if (error > 0.5) {
        y = y + ys;
        error -= 1;
      }
    }
  }
}

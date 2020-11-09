/**
 * VectorObject for drawing horizontal lines.
 */
public class HLine extends VectorObject {
  final int xLength;

  public HLine(int id, int x, int y, int xLength) {
    super(id, x, y);
    this.xLength = xLength;
  }

  @Override
  public void draw(char[][] matrix) {
    final int endX = this.x + xLength;
    for (int posX = this.x; posX < endX; posX++)
      matrix[this.y][posX] = '*';
  }
}

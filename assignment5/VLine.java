/**
 * VectorObject for drawing vertical lines
 */
public class VLine extends VectorObject {
  final int yLength;

  public VLine(int id, int x, int y, int yLength) {
    super(id, x, y);
    this.yLength = yLength;
  }

  @Override
  public void draw(char[][] matrix) {
    final int endY = this.y + yLength;

    for (int posY = this.y; posY < endY; posY++)
      matrix[posY][this.x] = '*';
  }
}

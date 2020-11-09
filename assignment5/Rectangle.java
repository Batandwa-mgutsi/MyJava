import java.util.LinkedList;
import java.util.List;

/**
 * VectorObject for drawing rectangle
 */
public class Rectangle extends VectorObject {
  final int xLength;
  final int yLength;

  final List<HLine> lines = new LinkedList<>();

  public Rectangle(int id, int x, int y, int xLength, int yLength) {
    super(id, x, y);
    this.xLength = xLength;
    this.yLength = yLength;

    this.computeLines();
  }

  @Override
  void setNewCoords(int newx, int newy) {
    super.setNewCoords(newx, newy);
    this.computeLines();
  }

  @Override
  public void draw(char[][] matrix) {
    for (HLine line : lines)
      line.draw(matrix);
  }

  private void computeLines() {
    lines.clear();

    for (int posY = this.y; posY < (this.y + this.yLength); posY++)
      lines.add(new HLine(0, x, posY, xLength));
  }
}

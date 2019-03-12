package pl.duch.articles.solar_sys.math;

import java.util.Arrays;

public class Matrix {

    private float[] data;

    public Matrix() {
        this.data = new float[3 * 3];
        setIdentity();
    }

    public void setIdentity() {
        Arrays.fill(data, 0);
        data[0] = data[4] = data [8] = 1.0f;
    }

    public void setTranslate(float tx, float ty) {
        setIdentity();
        data[2] = tx;
        data[5] = ty;
    }

    public void setRotate(float angle) {
        setIdentity();
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);

        data[0] = cos;
        data[1] = -sin;
        data[3] = sin;
        data[4] = cos;
    }


    public static void mul(Matrix lm, Matrix rm, Matrix result) {
        for(int row = 0; row<3; row++) {
            for(int col = 0; col<3; col++) {
                result.data[row * 3 + col] =
                                lm.data[row * 3 + 0] * rm.data[col + 0]  +
                                lm.data[row * 3 + 1] * rm.data[col + 3] +
                                lm.data[row * 3 + 2] * rm.data[col + 6];
            }
        }
    }

    public Matrix mulVector(float[] source, int startOffset,
                                         float[] dest, int endOffset, int count) {
        for(int i=startOffset; i<startOffset + count * 3; i+=3, endOffset+=3) {
            for(int j=0; j<3; j++) {
                dest[endOffset + j] =
                              data[j * 3 + 0] * source[i + 0] +
                              data[j * 3 + 1] * source[i + 1] +
                              data[j * 3 + 2] * source[i + 2];
            }

        }

        return this;
    }

}

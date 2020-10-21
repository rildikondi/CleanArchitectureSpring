package com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Gaussian elimination with partial pivoting.
 */
public class GaussSolver {
    private static final double EPSILON = 1e-10;

    // name and port of server
    final static String URL = "localhost";
    final static int port = 9999;

    // debugFlag
    private static boolean debugFlag = false;

    // number of threads
    static int T;

    // thread pool
    static ExecutorService pool;

    // input/output data for threads
    static double[][] A0;
    static double[] b0;

    static class RowThread implements Callable<Integer> {

        int tRow;
        int p;
        int N;

        public RowThread(int tRow, int p, int N) {
            this.tRow = tRow;
            this.p = p;
            this.N = N;

            if (debugFlag) {
                System.out.println("this.tRow  = " + this.tRow);
                System.out.println("this.p     = " + this.p);
                System.out.println("this.N     = " + this.N);
                System.out.println("this.toString() = " + this.toString());
            }
        }

        public Integer call() throws Exception {
            if (this.tRow < this.N) {

                int row = this.tRow;
                int p = this.p;
                int N = this.N;

                double alpha = A0[row][p] / A0[p][p];
                b0[row] -= smult(alpha, b0[p]);
                for (int j = p; j < N; j++) {
                    A0[row][j] -= smult(alpha, A0[p][j]);
                }

            }

            return Integer.valueOf(0);
        }

    } // static class RowThread

    // Gaussian elimination with partial pivoting
    public static double[] solve(double[][] A, double[] b) {
        int N = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            /*      We perform the eliminations across these rows concurrently.   */
            A0 = A;
            b0 = b;

            // pivot within A and b
            //              for (int row = p + 1; row < N; row++) {
            for (int row = p + 1; row < N; row++) {

                Vector<Future<Integer>> result = new Vector<Future<Integer>>(T);

                double factor = A[row][p] / A[p][p];
                b0[row] -= factor * b0[p];
                for (int j = p; j < N; j++)
                    A[row][j] -= factor * A[p][j];

//        for (int i = 0; i < T; i++) {
//          int tRow = row + i;
//          RowThread th = new RowThread(tRow, p, N);
//          Future<Integer> it = pool.submit(th);
//          result.add(it);
//        }
//
//        try {
//          for (int i = 0; i < T; i++) {
//            int value = result.get(i).get().intValue();
//            if (debugFlag) {
//              System.out.println("value = " + value);
//            }
//          }
//        } catch (InterruptedException e) {
//        } catch (ExecutionException e) {
//        }

            }

            /*           We wait until all threads are done with this stage.      */
            /*          We then proceed to handle the next normalisation row.     */
        }

        // back substitution
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += smult(A[i][j], x[j]);
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    /* "slow multiplication" operation */
    public static double smult(double a, double b) {
        double c = 0;
        for (int i = 0; i < 100000; i++) {
            c = c * c;
        }
        return a * b + c;
    }

    /* Initialize A and B (and X to 0.0s) */
    public static void initialize_inputs(double[][] A, double[] b) {
        int N = b.length;
        int row, col;

        //        long seed = 9256702;
        long seed = 4;
        Random generator = new Random(seed);

        System.out.printf("\nInitializing...\n");
        for (col = 0; col < N; col++) {
            for (row = 0; row < N; row++) {
                A[row][col] = generator.nextDouble();
            }
            b[col] = generator.nextDouble();
            //            X[col] = 0.0;
        }

    }

    /* Print results */
    public static void print_results(double[] x) {
        int N = x.length;
        int col;

        if (N < 10) {
            System.out.print("\nx = [ ");
            for (col = 0; col < N; col++) {
                System.out.print(x[col]);
                if (col < N - 1) {
                    System.out.print("; ");
                } else {
                    System.out.print(" ]\n");
                }
            }
        }
    }

    /* Print input matrices */
    public static void print_inputs(double[][] A, double[] b) {
        int N = b.length;
        int row, col;

        if (N < 10) {
            System.out.print("\nA = [\n\t");
            for (row = 0; row < N; row++) {
                for (col = 0; col < N; col++) {
                    System.out.print(A[row][col]);
                    if (col < N - 1) {
                        System.out.print(", ");
                    }
                }
                if (row < N - 1) {
                    System.out.print(";\n\t");
                } else {
                    System.out.print("\n    ]\n");
                }
            }
            //            System.out.print("\nB = [ ");
            System.out.print("\nb = [ ");
            for (col = 0; col < N; col++) {
                System.out.print(b[col]);
                if (col < N - 1) {
                    System.out.print("; ");
                } else {
                    System.out.print(" ]\n");
                }
            }
        }
    }

    //  public static void main(String[] args) {
    public static void execGaussianElimination(String[] args) {
        int N = 64;

        if (args.length != 1) {
            System.out.println("Usage: GaussSolver <number of threads>");
            return;
        }
        try {
            T = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument (number expected): " + args[0]);
            return;
        }

        if (T == 0) {
            N = 4;
            T = 2;
        }

        pool = Executors.newFixedThreadPool(T);

        double[][] A = new double[N][N];
        double[] b = new double[N];
        initialize_inputs(A, b);
        print_inputs(A, b);

        if (debugFlag) {
            /* Start Clock */
            System.out.printf("\nStarting clock.\n");
        }
        long t0 = System.currentTimeMillis();

        double[] x = solve(A, b); // Gaussian elimination

        long t1 = System.currentTimeMillis();
        if (debugFlag) {
            System.out.printf("Stopped clock.\n");
        }

        // print results
        print_results(x);

        long elapsedTime = t1 - t0;
        if (debugFlag) {
            System.out.println("\nElapsed Time = " + elapsedTime + " ms.");
        }

        pool.shutdown();
        // Wait until all threads are finish
        while (!pool.isTerminated()) {

        }
        if (debugFlag) {
            System.out.printf("--------------------------------------------\n");
        }

    }

    private static String getProgramName() {
        String s = new GaussSolver().getClass().getName();
        return s;
    }

    // ----------------------------------------------------------------------------
    //
    // main program
    //
    // ----------------------------------------------------------------------------
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: " + getProgramName() + " -client | -server");
            return;
        }

        if (args[0].equals("-client"))
            client();
        else
            server();
    }

    // ----------------------------------------------------------------------------
    //
    // client code
    //
    // ----------------------------------------------------------------------------
    static void client() {
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = console.readLine();
                if (line == null)
                    return;

                Socket socket = new Socket(URL, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                /* Start Clock */
                System.out.printf("\nStarting clock.\n");
                long t0 = System.currentTimeMillis();

                out.println(line);
                out.flush();

                String answer = in.readLine();
                if (answer == null)
                    System.out.println("empty result");
                else
                    System.out.println("answer: " + answer);

                /* Stop Clock */
                long t1 = System.currentTimeMillis();
                System.out.printf("Stopped clock.\n");

                long elapsedTime = t1 - t0;
                System.out.println("\nElapsed Time = " + elapsedTime + " ms.");

            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            System.exit(-1);
        }
    }

    // ----------------------------------------------------------------------------
    //
    // server code
    //
    // ----------------------------------------------------------------------------
    static void server() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                try {
                    T = Integer.parseInt(in.readLine());
                } catch (NumberFormatException e) {
                    out.println("Invalid argument: " + e.getMessage());
                    out.flush();
                    continue;
                }

                System.out.println("Started");

                String[] myargs = new String[1];
                myargs[0] = String.valueOf(T);
                execGaussianElimination(myargs);

                System.out.println("Done");

                out.println("Done");
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            System.exit(-1);
        }
    }
}

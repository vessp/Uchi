package vessp.com.uchi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends Activity
{
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.send);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                new SocketTask().execute();

            }
        });

    }




    private class SocketTask extends AsyncTask<URL, Integer, Long>
    {
        protected Long doInBackground(URL... urls)
        {
            Socket socket = null;
            OutputStream out = null;
            PrintWriter output = null;
            try
            {
                socket = new Socket("192.168.0.2", 8889);

                out = socket.getOutputStream();
                output = new PrintWriter(out);

//                status.setText("Sending Data to PC");
                output.println("Hello from Android");
//                status.setText("Data sent to PC");

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    output.flush();
                    output.close();
                    //out.flush();
                    //out.close();

                    socket.close();
//                    status.setText("Socket closed");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            return 0l;
        };

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Long result) {

        }
    }


























    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

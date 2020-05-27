package com.itesm.tarea3

import android.os.Handler
import android.os.Message
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

open class Request(val url: String, var handler: Handler) : Thread() {
    override fun run() {
        super.run()
        try {
            val direccion = URL(url)
            val connection = direccion.openConnection() as HttpURLConnection
            val code = connection.responseCode

            if (code == HttpURLConnection.HTTP_OK) {
                val `is` = connection.inputStream
                var br = BufferedReader(InputStreamReader(`is`))

                val builder = StringBuilder()
                var lineaActual: String?

                do{
                    lineaActual = br.readLine()
                    if(lineaActual == null){
                        break
                    }
                    builder.append(lineaActual)
                } while (true)

                var json = builder.toString()
                Log.wtf("REQUEST", json)
                var resulado = JSONArray(json)
                var mensaje = Message()
                mensaje.obj = resulado
                handler.sendMessage(mensaje)
            }

        }catch (e: MalformedURLException) {
            e.printStackTrace();
        } catch (e: IOException) {
            e.printStackTrace();
        } catch (e: JSONException) {
            e.printStackTrace();
        }
    }
}
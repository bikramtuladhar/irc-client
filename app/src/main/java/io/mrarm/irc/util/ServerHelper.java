package io.mrarm.irc.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import io.mrarm.irc.R;
import io.mrarm.irc.ServerConnectionInfo;
import io.mrarm.irc.ServerConnectionManager;
import io.mrarm.irc.config.ServerConfigData;
import io.mrarm.irc.config.ServerConfigManager;

public class ServerHelper {


    public static void save(Context context) {
        ServerConfigData mEditServer = null;

        boolean addConnection = false;
        if (mEditServer == null) {
            mEditServer = new ServerConfigData();
            mEditServer.uuid = UUID.randomUUID();
        } else {
            ServerConnectionInfo conn = ServerConnectionManager.getInstance(context).getConnection(mEditServer.uuid);
            if (conn != null) {
                conn.disconnect();
                ServerConnectionManager.getInstance(context).removeConnection(conn);
                addConnection = true;
            }
        }
        mEditServer.name = "ChatSansar";
        mEditServer.address = "irc.chatsansar.com";
        mEditServer.port = 6697;
        mEditServer.ssl = true;
        mEditServer.nicks = new ArrayList<String>();
        String nickName = "CsUser-" + RandomGenerator.usingMathClass();

        mEditServer.nicks.add(nickName);
        mEditServer.nicks.add(nickName+'_');
        if (mEditServer.nicks.size() == 0)
            mEditServer.nicks = null;

        mEditServer.user = nickName;
        mEditServer.realname = nickName;
        mEditServer.pass = null;
        mEditServer.authMode = null;
        mEditServer.authUser = null;
        mEditServer.authPass = null;


        try {
            ServerConfigManager.getInstance(context).saveServer(mEditServer);
        } catch (IOException e) {
            Log.e("ServerHelper", "Failed to save server info");
            e.printStackTrace();
            return;
        }
        if (addConnection) {
            ServerConnectionManager.getInstance(context).tryCreateConnection(mEditServer, context);
        }
    }
}

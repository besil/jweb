package it.besil.jweb.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import it.besil.jweb.app.JWebApp;
import it.besil.jweb.app.handlers.JWebHandler;
import it.besil.jweb.app.protocol.answer.Answer;
import it.besil.jweb.app.protocol.answer.MessageAnswer;
import it.besil.jweb.app.protocol.payloads.Payload;
import it.besil.jweb.app.resources.HttpMethod;
import it.besil.jweb.app.resources.JWebController;
import it.besil.jweb.server.conf.JWebConfiguration;

import java.io.BufferedReader;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by besil on 09/08/2016.
 */
public class SimpleApp extends JWebApp {
    public SimpleApp(JWebConfiguration jwebConf) {
        super(jwebConf);
    }

    @Override
    public List<? extends JWebController> getControllers() {
        return Arrays.asList(new JWebController(getJWebConf()) {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.get;
            }

            @Override
            public JWebHandler getHandler() {
                return new JWebHandler<SimplePayload, SimpleAnswer>(SimplePayload.class, SimpleAnswer.class) {
                    @Override
                    public SimpleAnswer process(SimplePayload sp) {
                        return new SimpleAnswer();
                    }
                };
            }

            @Override
            public String getPath() {
                return "/simple";
            }
        }, new JWebController(getJWebConf()) {

            @Override
            public HttpMethod getMethod() {
                return HttpMethod.post;
            }

            @Override
            public JWebHandler getHandler() {
                return new JWebHandler<SimplePayload, MessageAnswer>(SimplePayload.class, MessageAnswer.class) {

                    @Override
                    public MessageAnswer process(SimplePayload payload) {
                        return new MessageAnswer("ciao mondo");
                    }
                };
            }

            @Override
            public String getPath() {
                return "/simple";
            }
        });
    }

    public static class SimpleAnswer implements Answer {
        private final String simple = "simple";
        private final int num = 5;
//        @RestDoc(name = "reader")
        private BufferedReader brd;
        private Date date = new Date();
        private List<Date> dates;
//        @RestDoc(name = "readers")
        private List<BufferedReader> readers;
//        private SimplePayload payload;
        private List<SimplePayload> payloads;

        public String getSimple() {
            return simple;
        }

        public int getNum() {
            return num;
        }

        public BufferedReader getBrd() {
            return brd;
        }

        public void setBrd(BufferedReader brd) {
            this.brd = brd;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<Date> getDates() {
            return dates;
        }

        public void setDates(List<Date> dates) {
            this.dates = dates;
        }

        public List<BufferedReader> getReaders() {
            return readers;
        }

        public void setReaders(List<BufferedReader> readers) {
            this.readers = readers;
        }

//        public SimplePayload getPayload() {
//            return payload;
//        }

//        public void setPayload(SimplePayload payload) {
//            this.payload = payload;
//        }

        public List<SimplePayload> getPayloads() {
            return payloads;
        }

        public void setPayloads(List<SimplePayload> payloads) {
            this.payloads = payloads;
        }
    }

    public static class SimplePayload implements Payload {
        private String simple;
        private int secret;
        private List<String> apps = Arrays.asList("ciao", "mondo");
        private Date date;
        @JsonProperty
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        @JsonSerialize(using = ZonedDateTimeSerializer.class)
        private ZonedDateTime zdt;
        private List<List<Integer>> numsofnums = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));

        public ZonedDateTime getZdt() {
            return zdt;
        }

        public void setZdt(ZonedDateTime zdt) {
            this.zdt = zdt;
        }

        public String getSimple() {
            return simple;
        }

        public void setSimple(String simple) {
            this.simple = simple;
        }

        public int getSecret() {
            return secret;
        }

        public void setSecret(int secret) {
            this.secret = secret;
        }

        public List<String> getApps() {
            return apps;
        }

        public void setApps(List<String> apps) {
            this.apps = apps;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<List<Integer>> getNumsofnums() {
            return numsofnums;
        }

        public void setNumsofnums(List<List<Integer>> numsofnums) {
            this.numsofnums = numsofnums;
        }

//        @Override
//        public void init(Request req, Response resp) {
//            this.simple = req.queryParams("simple");
//        }
    }
}

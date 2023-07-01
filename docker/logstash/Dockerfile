FROM docker.elastic.co/logstash/logstash:8.7.0

#RUN rm -f /usr/share/logstash/pipeline/logstash.conf

# Pipeline pointing configuration
COPY ./logstash.conf /usr/share/logstash/pipeline/

# Configuration pipeline and monitoring elasticsearch for sending logs 
#COPY ./logstash.yml /usr/share/logstash/config/


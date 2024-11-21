FROM nginx:latest
RUN echo '<h1>This is nginx container with image dockerfile</h1>' > /usr/share/nginx/html/index.html
EXPOSE 80
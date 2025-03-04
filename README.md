## 开发环境

**1. 安装python 3.11或者3.12环境**:

**2. 安装poetry**:  
  ```shell
  pip install poetry
  ```

**3. 配置poetry虚拟环境**:  
  ```shell
  poetry env activate
  ```

**4. 启动后端接口服务**: 
  ```shell
  点击 vscode 的调试按钮启动即可
  ```

**5. 启动前端服务**: 
  ```shell
  make start-web-dev
  ```

## 正式环境

**0. 管理员用户名密码**: 
  ```shell
  # 用户名 admin@hollysmart.com
  # 密码 Passw0rd1@qaz
  ```

**1. xshell连接服务器**: 

**2. 执行命令**: 
  ```shell
  # 切换到目录
  cd /root/dify
  # 最新代码拉取
  git pull origin master
  # 修改根目录下面的 Makefile 文件中的 VERSION=latest 版本号
  # 执行编译
  make build-all
  # 切换到docker目录
  cd /root/dify/docker
  # 修改docker-compose.yaml文件中镜像的版本信息，比如 image: langgenius/dify-api:latest 修改为 image: langgenius/dify-api:[上一步修改的版本号]
  # 然后重新启动dify
  docker compose up -d
  ```
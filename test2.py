# חיבור למתג דרך SSH

import paramiko

def connect_to_switch(ip, username, password, commands):
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(ip, username=username, password=password)

    for command in commands:
        stdin, stdout, stderr = ssh.exec_command(command)
        print(f"Executing command: {command}")
        
        print(stdout.read().decode())
        print(stderr.read().decode())

    ssh.close()

if __name__ == "__main__":
    switch_ip = ""
    username = ""
    password = ""
    commands = [
        "show version"
    ]

    connect_to_switch(switch_ip, username, password, commands)

# חיבור למתג דרך com line


import serial
import time

def connect_to_switch(port, baudrate, commands):
    try:
       ser = serial.Serial(port, baudrate, timeout=1)
       time.sleep(2)
       for command in commands:
            ser.write((command + '\n').encode())
            time.sleep(1) 
            output = ser.read(ser.inWaiting()).decode()  
            print(f"Executing command: {command}")
            print(f"output: {output}")

        ser.close()

    except serial.SerialException as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    serial_port = "com"  
    baudrate = 9600  
    commands = [
        "show version"
    ]

    connect_to_switch(serial_port, baudrate, commands)

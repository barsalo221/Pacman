*חיבור למתג דרך com line*


import serial
import time

def connect_to_switch(port, baudrate, commands):
    try:
        # Establish the serial connection
        ser = serial.Serial(port, baudrate, timeout=1)
        
        # Give the connection a second to settle
        time.sleep(2)
        
        # Send each command to the switch
        for command in commands:
            ser.write((command + '\n').encode())  # Send command
            time.sleep(1)  # Give some time for the command to execute
            output = ser.read(ser.inWaiting()).decode()  # Read the output
            print(f"Executing command: {command}")
            print(output)

        # Close the serial connection
        ser.close()

    except serial.SerialException as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    serial_port = "/dev/ttyUSB0"  # Update with your serial port
    baudrate = 9600  # Update with the correct baudrate
    commands = [
        "show version",
        "show ip interface brief",
        "configure terminal",
        "interface GigabitEthernet0/1",
        "shutdown",
        "exit",
        "write memory"
    ]

    connect_to_switch(serial_port, baudrate, commands)
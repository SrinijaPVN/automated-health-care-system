class HealthMonitoringSystem:
    def __init__(self):
        self.patient_records = {}

    def add_patient(self, name, age, condition):
        if name in self.patient_records:
            print(f"Patient {name} already has a report.")
        else:
            self.patient_records[name] = {
                'Age': age,
                'Condition': condition
            }
            print(f"Patient {name}'s report added successfully.")

    def retrieve_report(self, name):
        if name in self.patient_records:
            print(f"Report for {name}:")
            print(f"Age: {self.patient_records[name]['Age']}")
            print(f"Condition: {self.patient_records[name]['Condition']}")
        else:
            print(f"No report found for patient {name}.")

    def display_all_reports(self):
        if not self.patient_records:
            print("No patient records available.")
        else:
            print("All Patient Reports:")
            for name, details in self.patient_records.items():
                print(f"\nName: {name}")
                print(f"Age: {details['Age']}")
                print(f"Condition: {details['Condition']}")

def main():
    system = HealthMonitoringSystem()

    while True:
        print("\n--- Automatic Health Monitoring System ---")
        print("1. Add New Patient Report")
        print("2. Retrieve Patient Report")
        print("3. Display All Reports")
        print("4. Exit")

        choice = input("Enter your choice: ")

        if choice == '1':
            name = input("Enter patient's name: ")
            age = input("Enter patient's age: ")
            condition = input("Enter patient's health condition: ")
            system.add_patient(name, age, condition)

        elif choice == '2':
            name = input("Enter patient's name: ")
            system.retrieve_report(name)

        elif choice == '3':
            system.display_all_reports()

        elif choice == '4':
            print("Exiting the system. Goodbye!")
            break

        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()

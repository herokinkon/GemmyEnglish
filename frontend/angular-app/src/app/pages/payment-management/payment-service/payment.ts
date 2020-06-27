import { Student } from '../../student-management/student-service/student';
import { Class } from '../../class-management/class-service/class';

export interface Payment {
    id: number;
    date: Date;
    discount: string;
    kindOfPayment: string;
    month: number;
    reason: string;
    amount: number;
    status: boolean;
    studentInfo: Student;
    classes: Class;
}

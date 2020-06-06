import { Classes } from '../../class-management/class-service/classes-model';
import { Student } from '../../student-management/student-service/student';

export interface Exam {
    id: number;
    name: string;
    examType: string;
    description: string
}

export interface ExamResult {
    id: number;
    examDate: Date;
    reading: number;
    writing: number;
    listening: number;
    speaking: number;
    overall: number;
    result: Boolean;
    exam: Exam;
    studentInfo: Student;
    classes: Classes
}
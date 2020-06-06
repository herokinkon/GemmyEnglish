import { Student } from '../../student-management/student-service/student';

export interface Classes {
    id: number;
    className: string;
    classCode: string;
    startDate: Date;
    endDate: Date;
    status: boolean;
    description: string;
    fee: string;
    courseId: number;
    studentInfos: Student[];
}

export class Course {
    id: number;
    name: string;
    description: string;
}

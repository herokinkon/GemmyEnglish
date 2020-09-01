import { Student } from '../../student-management/student-service/student';
import { Staff } from '../../staff-management/staff-service/staff';

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
    basis: string;
    address: string;
	room: string
    studentInfos: Student[];
    staffInfos: Staff[];
}

export class Course {
    id: number;
    name: string;
    description: string;
}

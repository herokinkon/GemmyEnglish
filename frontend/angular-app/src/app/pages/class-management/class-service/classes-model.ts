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
    campus: string;
    address: string;
    room: string
    schedule: string;
    lesson: number;
    studentInfos: Student[];
    staffInfos: Staff[];
}

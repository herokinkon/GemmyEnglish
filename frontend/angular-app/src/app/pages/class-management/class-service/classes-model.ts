export interface Classes {
    id: number;
    className: string;
    classCode: string;
    startDate: Date;
    endDate: Date;
    status: boolean;
    description: string;
    fee: string
    courseId: number;
}

export class Course {
    id: number;
    name: string;
    description: string;
}

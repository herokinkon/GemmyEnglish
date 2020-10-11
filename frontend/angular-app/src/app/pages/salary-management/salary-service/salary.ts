export interface OtherOutcome {
    id: number;
    fullName: string;
    birthday: Date;
    usedDate: Date;
    purpose: string;
    cost: string;
    status: boolean;
    staffId: number
}

export interface SalaryPartTimeResult {
    workDay: number;
    dayOfWeek: string;
    startTime: Date;
    endTime: Date;
    hour: string;
    salaryOfDate: string;
}
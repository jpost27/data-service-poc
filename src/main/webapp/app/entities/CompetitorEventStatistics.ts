import { CompetitorsEvents } from './CompetitorsEvents';
export interface CompetitorEventStatistics {
    competitorsEventsId: number;
    statisticTypeCode: string;
    statisticValue: number;
    competitorsEvents?: CompetitorsEvents;
}

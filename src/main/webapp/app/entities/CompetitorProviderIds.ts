import { Competitors } from './Competitors';
import { CompetitorSportsbookSelectionIds } from './CompetitorSportsbookSelectionIds';
export interface CompetitorProviderIds {
    competitorId: number;
    fanduelCompetitorId?: number;
    numberfireCompetitorId?: number;
    sportradarCompetitorId?: string;
    competitors?: Competitors;
    competitorSportsbookSelectionIds?: CompetitorSportsbookSelectionIds[];
}
